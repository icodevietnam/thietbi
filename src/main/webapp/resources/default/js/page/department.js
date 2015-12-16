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
				required:"Tên is not blank"
			},
			description:{
				required:"Chú thích is not blank"
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
				required:"Tên is not blank"
			},
			description:{
				required:"Chú Thích is not blank"
			}
		},
	});
});

function displayTable() {
	var dataDepartments = [];
	$.ajax({
		url : "/thietbi/department/getAll",
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
								+ value.id + ")' >Edit</button>",
						"<button class='btn btn-sm btn-danger' onclick='deleteItem("
								+ value.id + ")'>Delete</button>" ]);
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
		url : "/thietbi/department/get",
		type : "GET",
		data : {
			itemId : id
		},
		dataType : "JSON",
		success : function(response) {
			$("#updateItemForm .departmentId").val(response.id);
			$("#updateItemForm .departmentName").val(response.name);
			$("#updateItemForm .departmentDescription").val(response.description);
			$("#updateItem").modal("show");
		}
	});
}

function deleteItem(id) {
	if (confirm("Are you sure you want to proceed?") == true) {
		$.ajax({
			url : "/thietbi/department/delete",
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
		var departmentId = $("#updateItemForm .departmentId").val();
		var departmentName = $("#updateItemForm .departmentName").val();
		var departmentDescription = $("#updateItemForm .departmentDescription").val();
		$.ajax({
			url : "/thietbi/department/update",
			type : "POST",
			data : {
				departmentId : departmentId,
				departmentName : departmentName,
				departmentDescription : departmentDescription
			},
			dataType : "JSON",
			success : function(response) {
			},complete:function(){
				displayTable();
				$("#updateItemForm .departmentId").val(" ");
				$("#updateItemForm .departmentName").val(" ");
				$("#updateItemForm .departmentDescription").val(" ");
				$("#updateItem").modal("hide");
			}
		});
	}
}

function insertItem() {
	if($("#newItemForm").valid()){
		var departmentName = $("#departmentName").val();
		var departmentDescription = $("#departmentDescription").val();
		$.ajax({
			url : "/thietbi/department/new",
			type : "POST",
			data : {
				departmentName : departmentName,
				departmentDescription : departmentDescription
			},
			dataType : "JSON",
			success : function(response) {
			},
			complete : function(){
				displayTable();
				$("#newItem").modal("hide");
				$("#departmentName").val(" ");
				$("#departmentDescription").val(" ");
			}
		});
	}
}
