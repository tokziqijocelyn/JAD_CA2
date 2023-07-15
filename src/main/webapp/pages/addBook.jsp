<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>BokStore</title>
</head>
<body>

	<%@page import='classes.Category'%>
	<%@page import='classes.Author'%>
	<%@page import='classes.Publisher'%>
	
	<%@page import='java.sql.*'%>
	<%@page import='java.util.ArrayList'%>
	<%@page import='models.CategoryDAO'%>
	<%@page import='models.AuthorDAO'%>
	<%@page import='models.PublisherDAO'%>
	<%@include file="../adminNav.jsp"%>
	<div class="container mt-4">
		<form action="/JAD_CA2/addBook" method="POST" enctype="multipart/form-data">
			<div class="row">
				<div class="col-md-5">
					<div class="project-info-box mt-0">
						<h2>
							<u><b>BOOK DETAILS</b></u>
						</h2>
						
							<input type="hidden" name="book_id"/>
							<p>
								<b><u>Title:</u></b> <input type="text" class="form-control" name="title"
									id="title" required autocomplete="off"  />
							</p>
							<p>
								<b><u>ISBN:</u></b> <input type="text" class="form-control"
									id="ISBN" required name="ISBN"
									autocomplete="off"  />
							</p>
							<p>
								<b><u>Price:</u></b> <input type="number" class="form-control"
									id="price"  required name="price"
									autocomplete="off"  />
							</p>
							<p>
								<b><u>Quantity:</u></b> <input type="number" class="form-control"
									id="quantity" required
									name="quantity" autocomplete="off"  />
							</p>
							<p>
								<b><u>Description:</u></b>
								<textarea class="form-control" id="description" required
									name="description" autocomplete="off" ></textarea>
							</p>
							<p>
								<b><u>Category:</u></b> <select class="form-select mb-2"
									id="category"  name="category">
									<%
									ArrayList<Category> categories = new CategoryDAO().getCategories();
									for(Category category: categories){
									%>
									<option
										value=<%=category.getCategory_id()%>><%=category.getCategory_name()%></option>
									<%
									}
									%>
								</select>
							</p>
							<p>
								<b><u>Author:</u></b> <select class="form-select mb-2"
									id="author"  name="author">
									<%
									ArrayList<Author> authors = new AuthorDAO().getAuthors();
									
									for (Author author : authors){
									%>
									<option
										value=<%=author.getAuthor_id()%>><%=author.getAuthor_name()%></option>
									<%
									}
									%>
								</select>
							</p>
							<p>
							<b><u>Publisher:</u></b> <select class="form-select mb-2"
								id="publisher" name="publisher">
								<%
								
								ArrayList<Publisher> publishers = new PublisherDAO().getPublisher();
								for (Publisher publisher : publishers) {
								%>
								<option
									value=<%=publisher.getPublisher_id()%>><%= publisher.getPublisherName() %></option>
								<%
								}
								%>
							</select>
						</p>
							<button id="editButton" type="submit" class="btn btn-primary">Save</button>
					</div>
				</div>
				
				<div class="col-md-7" >
					<div class="m-3" >
					    <h3 id="headingFile">Image upload</h3>
					    <h6>Only supports jpg, png and jpeg [limit:1mb]</h6>
					</div>
					 <input type="file" name="image" id="image" accept="image/*" class="custom-file-input">
					 <div class="m-3 p-3">
				     <img id="preview" src="../images/default.jpg" alt="Image Preview"  style="max-width: 500px; max-height: 500px; margin-left:100px">
				     </div>
				</div>
			</form>
	</div>
	
			<script>
		    document.getElementById("image").addEventListener("change", function (e) {
		        var file = e.target.files[0];
		        if (file) {
		            var reader = new FileReader();
		            reader.onload = function (e) {
		                document.getElementById("preview").src = e.target.result;
		            };
		            reader.readAsDataURL(file);
		        }
		    });
			</script>
</body>
</html>