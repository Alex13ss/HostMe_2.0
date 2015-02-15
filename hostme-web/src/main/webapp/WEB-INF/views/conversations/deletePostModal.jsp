<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div class="modal fade" id="modalRemovePost" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title" id="myModalLabel"><spring:message	code="post.removeMsg" /></h4>
						</div>
						<div class="modal-body">
							<div class="callout callout-danger" id="alert" align="center">
								<font size="5">
									<spring:message	code="post.submitRemove" />
								</font>
							</div>
						</div>
						<div class="modal-footer">
							<a href="" class="btn btn-primary removeBtn" id="removeConfirmButton">
								<spring:message	code="label.delete" />
							</a>
							<button type="button" class="btn btn-default"	data-dismiss="modal">
								<spring:message	code="label.cancel" />
							</button>
						</div>
					</div>
				</div>
			</div>