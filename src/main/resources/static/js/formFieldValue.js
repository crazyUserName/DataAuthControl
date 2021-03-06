var table;

function init(func) {
 	$("#formFieldValueBar").addClass("active");
 	 var tpl = $("#tpl").html();
    //预编译模板
    var template = Handlebars.compile(tpl);
    
    table = $('#table').DataTable({
    	"paging":   true,
        "ordering": true,
        "info":     true,
        "searching":true,
        "processing": false,
         "serverSide": true,
         "order":  [[ 1, "desc" ]],
         "ajax" :{
        	     "url": "/formFieldValue/findByPage0",
        	     "dataType": "json",
        	    "type": "POST",
        	     "data": function ( d ) {
    	    			return  {
        	    			"searchValue": 	d.search.value,
    	    				"orderColumn" : d.columns[d.order[0].column].data,
    	    				"orderDir":	d.order[0].dir,
    	    				"start": 	d.start,
    	    				"length":	d.length,
    	    				"draw"  :	d.draw,
    	    				"formFieldId" : $("#formFieldId").val()
    	    			};
	    	     }  
         	},
         	 "columns": [
        		  {"data": null },
        		  {"data": "id" },
                  {"data": "tagKey" },
                  {"data": "value" },
                  {"data": "fieldId" },
                  {"data": "orderBy" },
                  {"data": null }
              ],
          "columnDefs": [
	                  {
	                      "searchable": false,
	                      "orderable": false,
	                      "visible": false,
	                      "targets": [1]
	                  },
	                  {
	                      "targets": 6,
	                      "render": function (a, b, c, d) {
	                          var context = {
	                              'func': func(c.id)
	                          };
	                          var html = template(context);
	                          return html;
	                      }
	                  }
          ],
          'dom': "<'row'<'col-xs-2'l><'#mytool.col-xs-4'><'col-xs-6'f>r>" +"t" +
           				"<'row'<'col-xs-6'i><'col-xs-6'p>>",
          "initComplete": function () {
               /*  $("#mytool").append('<button id="datainit" type="button" class="btn btn-primary btn-sm">增加基础数据</button>&nbsp');
                $("#mytool").append('<button type="button" class="btn btn-default btn-sm" data-toggle="modal" data-target="#myModal">添加</button>');
                $("#datainit").on("click", initData); */
          }
    });
    
    //添加序号
    //不管是排序，还是分页，还是搜索最后都会重画，这里监听draw事件即可
    table.on('draw.dt',function() {
                table.column(0, {
                    search: 'applied',
                    order: 'applied'
                }).nodes().each(function(cell, i) {
                    //i 从0开始，所以这里先加1
                    i = i+1;
                    //服务器模式下获取分页信息
                    var page = table.page.info();
                    //当前第几页，从0开始
                    var pageno = page.page;
                    //每页数据
                    var length = page.length;
                    //行号等于 页数*每页数据长度+行号
                    var columnIndex = (i+pageno*length);
                    cell.innerHTML = columnIndex;
                });
            }).draw();
    
	$("#newFormFieldValue").click(function() {
		window.location.href = "/formFieldValue/goNew";
	});
    
};

function deleteById(id, obj) {
    if(confirm("确定删除此表单信息?")){
		$.ajax({
            type: "GET",
            url: "/formFieldValue/delete/"+id,
            success: function(data){
                var tr = $(obj).closest('tr');
                table.row( tr ).remove().draw( false );
            }
        });
	}
}

function update(id) {
    window.location.href = "/formFieldValue/goUpdate/" + id;
}

function goList() {
	window.location.href = "/formFieldValue/goList";
}