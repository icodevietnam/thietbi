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
		url : "/thietbi/contract/getAll",
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
		url : "/thietbi/contract/get",
		type : "GET",
		data : {
			itemId : id
		},
		dataType : "JSON",
		success : function(response) {
			$("#updateItemForm .contractId").val(response.id);
			$("#updateItemForm .contractName").val(response.name);
			$("#updateItemForm .contractDescription").val(response.description);
			$("#updateItem").modal("show");
		}
	});
}

function deleteItem(id) {
	if (confirm("Are you sure you want to proceed?") == true) {
		$.ajax({
			url : "/thietbi/contract/delete",
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
		var contractId = $("#updateItemForm .contractId").val();
		var contractName = $("#updateItemForm .contractName").val();
		var contractDescription = $("#updateItemForm .contractDescription").val();
		$.ajax({
			url : "/thietbi/contract/update",
			type : "POST",
			data : {
				contractId : contractId,
				contractName : contractName,
				contractDescription : contractDescription
			},
			dataType : "JSON",
			success : function(response) {
			},complete:function(){
				displayTable();
				$("#updateItemForm .contractId").val(" ");
				$("#updateItemForm .contractName").val(" ");
				$("#updateItemForm .contractDescription").val(" ");
				$("#updateItem").modal("hide");
			}
		});
	}
}

function insertItem() {
	if($("#newItemForm").valid()){
		var contractName = $("#contractName").val();
		var contractDescription = $("#contractDescription").val();
		$.ajax({
			url : "/thietbi/contract/new",
			type : "POST",
			data : {
				contractName : contractName,
				contractDescription : contractDescription
			},
			dataType : "JSON",
			success : function(response) {
			},
			complete : function(){
				displayTable();
				$("#newItem").modal("hide");
				$("#contractName").val(" ");
				$("#contractDescription").val(" ");
			}
		});
	}
}
