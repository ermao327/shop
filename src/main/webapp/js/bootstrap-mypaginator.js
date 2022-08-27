var myoptions = {
	bootstrapMajorVersion: 3, //bootstrap版本
    currentPage: 1, //设置当前页
    numberOfPages: 5, //设置控件显示的页码数
    totalPages: 10,  //设置总页数
  	size: "large",
	itemTexts: function(type, page, current) { //修改显示文字
        switch (type) {
        case "first":
            return "第一页";
        case "prev":
            return "上一页";
        case "next":
            return "下一页";
        case "last":
            return "最后一页";
        case "page":
            return page;
        }
    },
    pageUrl: null,
    onPageClicked: null
};
//初始化分页函数
function myPaginatorFun(ulItemId, currentpageNo, totals){
	myoptions.currentPage = currentpageNo;//重新设置当前页
	myoptions.totalPages = totals;//重新设置总页数
    $("#" + ulItemId).bootstrapPaginator(myoptions);
}