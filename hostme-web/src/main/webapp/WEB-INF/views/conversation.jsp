<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
							<div class="box box-success">
                                <div class="box-header ui-sortable-handle" style="cursor: move;">
                                    <i class="fa fa-comments-o"></i>
                                    <h3 class="box-title">${conversation.title}</h3>
                                </div>
                                <div class="slimScrollDiv" style="position: relative; overflow: hidden; width: auto; height: 250px;"><div class="box-body chat" id="chat-box" style="overflow: hidden; width: auto; height: 250px;">
                                	<c:forEach var="post" items="${conversation.posts}">
									<!-- chat item -->
                                    <div class="item">
                                        <img src="img/avatar.png" alt="user image" class="online">
                                        <p class="message">
                                            <a href="#" class="name">
                                                <small class="text-muted pull-right"><i class="fa fa-clock-o"></i>${post.postedAt.time}</small>
                                                ${post.author.firstName}&nbsp;${post.author.lastName}
                                            </a>
                                            ${post.content}
                                        </p>
                                        <!--div class="attachment">
                                            <h4>Attachments:</h4>
                                            <p class="filename">
                                                Theme-thumbnail-image.jpg
                                            </p>
                                            <div class="pull-right">
                                                <button class="btn btn-primary btn-sm btn-flat">Open</button>
                                            </div>
                                        </div--!><!-- /.attachment -->
                                    </div><!-- /.item -->
                                    </c:forEach>
                                </div><div class="slimScrollBar" style="width: 7px; position: absolute; top: 0px; opacity: 0.4; display: none; border-radius: 0px; z-index: 99; right: 1px; height: 184.91124260355px; background: rgb(0, 0, 0);"></div><div class="slimScrollRail" style="width: 7px; height: 100%; position: absolute; top: 0px; display: none; border-radius: 0px; opacity: 0.2; z-index: 90; right: 1px; background: rgb(51, 51, 51);"></div></div><!-- /.chat -->
                                <div class="box-footer">
                                    <div class="input-group">
                                        <input class="form-control" placeholder="Type message...">
                                        <div class="input-group-btn">
                                            <button class="btn btn-success" style="height: 34px;"><i class="fa fa-plus"></i></button>
                                        </div>
                                    </div>
                                </div>
                            </div>
