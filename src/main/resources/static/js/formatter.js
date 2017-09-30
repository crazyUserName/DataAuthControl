Date.prototype.pattern=function(fmt) {         
    var o = {         
    "M+" : this.getMonth()+1, //月份         
    "d+" : this.getDate(), //日         
    "h+" : this.getHours()%12 == 0 ? 12 : this.getHours()%12, //小时         
    "H+" : this.getHours(), //小时         
    "m+" : this.getMinutes(), //分         
    "s+" : this.getSeconds(), //秒         
    "q+" : Math.floor((this.getMonth()+3)/3), //季度         
    "S" : this.getMilliseconds() //毫秒         
    };         
    var week = {         
    "0" : "/u65e5",         
    "1" : "/u4e00",         
    "2" : "/u4e8c",         
    "3" : "/u4e09",         
    "4" : "/u56db",         
    "5" : "/u4e94",         
    "6" : "/u516d"        
    };         
    if(/(y+)/.test(fmt)){         
        fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));         
    }         
    if(/(E+)/.test(fmt)){         
        fmt=fmt.replace(RegExp.$1, ((RegExp.$1.length>1) ? (RegExp.$1.length>2 ? "/u661f/u671f" : "/u5468") : "")+week[this.getDay()+""]);         
    }         
    for(var k in o){         
        if(new RegExp("("+ k +")").test(fmt)){         
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));         
        }         
    }         
    return fmt;         
} 


function formatterMany(params) {
	
	if(typeof(params) != 'object' || params.length==0){
		return;
	}
	
	if(typeof(params[0]) != 'object' || typeof(params[0].value) != 'object'){
		return;
	}
	
	 var ms = parseInt(params[0].value[0]);
	var res = new Date(ms).pattern("HH:mm:ss") + '<br/>';
       params.forEach(function (item) {
    	   res += '<span style="display:inline-block;margin-right:5px;border-radius:10px;width:9px;height:9px;background-color:' 
        	   + item.color + '"></span>'+item.seriesName + '  : ' + item.value[1]+'<br/>';
       });
   return res;
}

function formatterSingle(params) {
	var res = "";
    for (var i = params.length - 1; i >= 0; i--) {
            var ms = parseInt(params[i].value[0]);
            res += new Date(ms).pattern("HH:mm:ss") + '  : ' + params[i].value[1];
    }
    return res;
}