<template>
  <video id="videoElement" class="video"></video>
</template>

<script>
import flvjs from 'flv.js'

export default {
  name: 'flvjs',
  props: {
    src: {
      type: String,
      default: 'http://cyberplayerplay.kaywang.cn/cyberplayer/demo201711-L1.flv'
      // default: 'http://122.227.179.90:23505/live/video.flv?devid=aztest1&channel=1&devline=2'
      // default: 'http://192.168.9.12:8080/live?app=live&stream=stream'
    }
  },
  data () {
    return {
      flvPlayer: null,
    }
  },
  created() {
    // console.log('flvSrc:', flvSrc)
    if (flvjs.isSupported()) {
      
    }
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
      var videoElement = document.getElementById('videoElement')
      this.flvPlayer = flvjs.createPlayer({
        type: 'flv',
        // url: 'http://yunxianchang.live.ujne7.com/vod-system-bj/TLaf2cc9d469939803949187b46da16c45.flv'
        url: src,
        cors: true,
        isLive: true
      })
      this.flvPlayer.attachMediaElement(videoElement)
      this.flvPlayer.load()
      this.flvPlayer.play()
    }
  },
  mounted () {
    // let flvSrc = this.src
    // console.log('flvSrc:', flvSrc)
    // if (flvjs.isSupported()) {
    //   var videoElement = document.getElementById('videoElement')
    //   var flvPlayer = flvjs.createPlayer({
    //     type: 'flv',
    //     // url: 'http://yunxianchang.live.ujne7.com/vod-system-bj/TLaf2cc9d469939803949187b46da16c45.flv'
    //     url: flvSrc,
    //     cors: true,
    //     isLive: true
    //   })
    //   flvPlayer.attachMediaElement(videoElement)
    //   flvPlayer.load()
    //   flvPlayer.play()
    // }
  }
}
</script>

<style lang="less" scoped>
.video {
  width: 100%;
}
</style>
