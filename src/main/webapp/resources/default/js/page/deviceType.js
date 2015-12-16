$(function() {
	displayTable();
	
	$("#newItemForm").validate({
		rules : {
			name:{
				required:true
			},
			description:{
				required:true
			}
		},
		messages : {
			name:{
				required:"Name is not blank"
			},
			description:{
				required:"Description is not blank"
			}
		},
	});
	
	$("#updateItemForm").validate({
		rules : {
			name:{
				required:true
			},
			description:{
				required:true
			}
		},
		messages : {
			name:{
				required:"Name is not blank"
			},
			description:{
				required:"Description is not blank"
			}
		},
	});
});

function displayTable() {
	var dataDepartments = [];
	$.ajax({
		url : "/thietbi/deviceType/getAll",
		type : "GET",
		dataType : "JSON",
		success : function(response) {
			var i = 0;
			$.each(response, function(key, value) {
				i++;
				dataDepartments.push([
						i,
						value.name,value.description,
						"<button class='btn btn-sm btn-primary' onclick='editItem("
						+ value.id + ")' >Sửa</button>",
						"<button class='btn btn-sm btn-danger' onclick='deleteItem("
						+ value.id + ")'>Xoá</button>" ]);
			});
			$('#tblDepartment').dataTable({
				"bDestroy" : true,
				"bSort" : true,
				"bFilter" : true,
				"bLengthChange" : true,
				"bPaginate" : true,
				"sDom" : '<"top">rt<"bottom"flp><"clear">',
				"aaData" : dataDepartments,
				"aaSorting" : [],
				"aoColumns" : [ {
					"sTitle" : "Số"
				}, {
					"sTitle" : "Tên"
				}, {
					"sTitle" : "Chú thích"
				}, {
					"sTitle" : "Sửa"
				}, {
					"sTitle" : "Xoá"
				} ]
			});
		}
	});
}

function editItem(id) {
	$.ajax({
		url : "/thietbi/deviceType/get",
		type : "GET",
		data : {
			itemId : id
		},
		dataType : "JSON",
		success : function(response) {
			$("#updateItemForm .deviceTypeId").val(response.id);
			$("#updateItemForm .deviceTypeName").val(response.name);
			$("#updateItemForm .deviceTypeDescription").val(response.description);
			$("#updateItem").modal("show");
		}
	});
}

function deleteItem(id) {
	if (confirm("Are you sure you want to proceed?") == true) {
		$.ajax({
			url : "/thietbi/deviceType/delete",
			type : "POST",
			data : {
				itemId : id
			},
			dataType : "JSON",
			success : function(response) {
				displayTable();
			}
		});
	}
}

function editedItem() {
	if($("#updateItemForm").valid()){
		var deviceTypeId = $("#updateItemForm .deviceTypeId").val();
		var deviceTypeName = $("#updateItemForm .deviceTypeName").val();
		var deviceTypeDescription = $("#updateItemForm .deviceTypeDescription").val();
		$.ajax({
			url : "/thietbi/deviceType/update",
			type : "POST",
			data : {
				deviceTypeId : deviceTypeId,
				deviceTypeName : deviceTypeName,
				deviceTypeDescription : deviceTypeDescription
			},
			dataType : "JSON",
			success : function(response) {
			},complete:function(){
				displayTable();
				$("#updateItemForm .deviceTypeId").val(" ");
				$("#updateItemForm .deviceTypeName").val(" ");
				$("#updateItemForm .deviceTypeDescription").val(" ");
				$("#updateItem").modal("hide");
			}
		});
	}
}

function insertItem() {
	if($("#newItemForm").valid()){
		var deviceTypeName = $("#deviceTypeName").val();
		var deviceTypeDescription = $("#deviceTypeDescription").val();
		$.ajax({
			url : "/thietbi/deviceType/new",
			type : "POST",
			data : {
				deviceTypeName : deviceTypeName,
				deviceTypeDescription : deviceTypeDescription
			},
			dataType : "JSON",
			success : function(response) {
			},
			complete : function(){
				displayTable();
				$("#newItem").modal("hide");
				$("#deviceTypeName").val(" ");
				$("#deviceTypeDescription").val(" ");
			}
		});
	}
}
