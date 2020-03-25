/**
 * 获取token
 */
export const getToken = () => {
    let userInfo = getStore("userinfo")
    if (userInfo == null) return null
    return userInfo.token
}


export const setUserInfo = (userinfo) => {
    setStore('userinfo', userinfo)
} 

export const getUserInfo = () => {
    let userInfo = getStore("userinfo")
    return userInfo
}

export const getUserName = () => {
    let userinfo = getStore("userinfo")
    return userinfo.name;
}


export const clearLocalStorage = () => {
    localStorage.clear();
}


/**
 * 存储localStorage
 */
export const setStore = (name, content) => {
	if (!name) return;
	if (typeof content !== 'string') {
		content = JSON.stringify(content);
	}
	window.localStorage.setItem(name, content);
}

/**
 * 获取localStorage
 */
export const getStore = name => {
	if (!name) return;
	var value = window.localStorage.getItem(name);
    if (value !== null) {
        try {
            value = JSON.parse(value);
        } catch (e) {
            value = value;
        }
    }
    return value;
}

/**
 * 删除localStorage
 */
export const removeStore = name => {
	if (!name) return;
	window.localStorage.removeItem(name);
}

