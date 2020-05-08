<template>
  <video id="videoElement" class="video" ref="player" muted autoplay></video>
</template>

<script>
import flvjs from 'flv.js'

export default {
  name: 'flvjs',
  props: {
    src: {
      type: String,
      // default: 'http://cyberplayerplay.kaywang.cn/cyberplayer/demo201711-L1.flv'
      default: 'http://120.79.62.199:23505/live/video.ps?devid=612276&channel=1&devline=1'
      // default: 'http://122.227.179.90:23505/live/video.flv?devid=aztest1&channel=1&devline=2'
      // default: 'http://192.168.9.12:8080/live?app=live&stream=stream'

    }
  },
  data () {
    return {
      flvPlayer: null,
      id: "1",
      rtsp: "rtsp://120.79.62.199:23506/zyrh/play?devid=612276@channel=1"
    }
  },
  created() {
    // console.log('flvSrc:', flvSrc)
    // this.loadPlayer()
  },
  watch: {
      src: function(val) {
          console.log(val)
          if (this.flvPlayer != null) {
            this.flvPlayer.destroy();
          }
          this.src = val;
          this.loadPlayer(val)
      }
  },
  methods: {
    loadPlayer(src) {
      console.log('load')
      console.log(src)
      debugger
      var videoElement = document.getElementById('videoElement')
      if (flvjs.isSupported()) {
            let video = this.$refs.player;
            if (video) {
                this.player = flvjs.createPlayer({
                    type: "flv",
                    isLive: true,
                    url: `ws://47.103.61.148:7777/rtsp/${this.id}/?url=${this.rtsp}`
                });
                this.player.attachMediaElement(video);
                try {
                    this.player.load();
                    this.player.play();
                } catch (error) {
                    console.log(error);
                };
            }
            }
      // this.flvPlayer = flvjs.createPlayer({
      //   type: 'flv',
      //   // url: 'http://yunxianchang.live.ujne7.com/vod-system-bj/TLaf2cc9d469939803949187b46da16c45.flv'
      //   url: src,
      //   cors: true,
      //   isLive: true
      // })
      // this.flvPlayer.attachMediaElement(videoElement)
      // this.flvPlayer.load()
      // this.flvPlayer.play()
      // let video = this.$refs.player;
      // this.flvPlayer = flvjs.createPlayer({
      //               type: "flv",
      //               isLive: true,
      //               url: `ws://47.103.61.148:8888/rtsp/${this.id}/?url=${this.rtsp}`
      //           });
      //           this.flvPlayer.attachMediaElement(video);
      //           try {
      //               this.flvPlayer.load();
      //               this.flvPlayer.play();
      //           } catch (error) {
      //               console.log(error);
      //           };
      
    }
  },
  mounted () {
        this.loadPlayer();
    },
}
</script>

<style lang="less" scoped>
.video {
  width: 100%;
}
</style>
