const base = {
    get() {
        return {
            url : "http://localhost:8080/hangkongdingpiao/",
            name: "hangkongdingpiao",
            // 退出到首页链接
            indexUrl: 'http://localhost:8080/hangkongdingpiao/front/index.html'
        };
    },
    getProjectName(){
        return {
            projectName: "航空票务推荐系统"
        } 
    }
}
export default base
