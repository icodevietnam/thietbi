<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
		<div class="row">
			<div class="col-lg-8">
				<div class="ibox">
					<div class="ibox-content">
						<a href="<c:url value='/admin/district/list'/>" class="btn-link">
							<h2>Manage District</h2>
						</a>
						<button data-toggle="modal" data-target="#newItem"
							class="btn btn-sm btn-primary">Create District</button>
						<div class="table-responsive">
							<table id="tblDepartment"
								class="table table-bordered table-hover table-striped">
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="modal fade" id="newItem" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="district">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">Add District</h4>
					</div>
					<form id="newItemForm" class="form-horizontal"
						action="<c:url value='/admin/district/new'/>" method="POST">
						<div class="modal-body">
							<div class="form-group">
								<label for="name" class="col-sm-2 control-label">Name</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="districtName" name="name">
								</div>
							</div>
							<div class="form-group">
								<label for="name" class="col-sm-2 control-label">Description</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="districtDescription"
										name="description">
								</div>
							</div>
							<div class="form-group">
								<label for="name" class="col-sm-2 control-label">Image</label>
								<div class="col-sm-10">
									<input type="file" class="form-control" id="image" name="image">
									<img width="200px" class="imageDemo" src="#" />
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" onclick="insertItem();"
								class="btn btn-primary">Save</button>
							<button type="button" class="btn btn-default"
								data-dismiss="modal">Close</button>
						</div>
					</form>
				</div>
			</div>
		</div>
		<div class="modal fade" id="updateItem" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="district">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">Edit District</h4>
					</div>
					<form id="updateItemForm" class="form-horizontal"
						action="<c:url value='/admin/profile/updateProfile'/>"
						method="POST">
						<div class="modal-body">
							<input type="text" class="districtId form-control hide"
								class="districtId" name="districtId">
							<div class="form-group">
								<label for="name" class="col-sm-2 control-label">Name</label>
								<div class="col-sm-10">
									<input type="text" class="districtName form-control"
										 name="name">
								</div>
							</div>
							<div class="form-group">
								<label for="name" class="col-sm-2 control-label">Description</label>
								<div class="col-sm-10">
									<input type="text" class="districtDescription form-control"
										 name="description">
								</div>
							</div>
							<div class="form-group">
								<label for="name" class="col-sm-2 control-label">Image</label>
								<div class="col-sm-10">
									<input type="file" class="fileUpload form-control" id="image" name="image">
									<img width="200px" class="imageDemo" src="#" />
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" onclick="editedItem();"
								class="btn btn-primary">Edit</button>
							<button type="button" class="btn btn-default"
								data-dismiss="modal">Close</button>
						</div>
					</form>
				</div>
			</div>
		</div>
		<script src="<c:url value='/resources/default/js/page/district.js'/>"></script>
	</tiles:putAttribute>
</tiles:insertDefinition>