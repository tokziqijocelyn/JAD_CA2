
	<%@page import='classes.*'%>
	<%@page import='models.PromoDAO'%>
	<%@page import='models.BookDAO'%>
	<%@page import="java.time.LocalDate"%>
	<%@page import="java.time.DayOfWeek"%>
	<%@page import="java.util.ArrayList"%>
	<%@page import="java.text.DecimalFormat"%>

	<%
	String visible = "visible";
	String color = "#C7CAFF";
	String icon = "fa-solid fa-face-smile:";
	DecimalFormat decforr = new DecimalFormat("0.00"); 
	
	//handling promotions =========================
	Promo promo = null;
	
    LocalDate today = LocalDate.now();
    int day = today.getDayOfWeek().getValue();
    if (day == 7) {
        day = 0; 
    }
	try {
		ArrayList<Book> discountedBooks = new BookDAO().getDiscountedBooks(day);
		promo = new PromoDAO().getCurrentPromotion();

		switch (day) {
		case 1:
			color = "#C7CAFF";
			icon = "fa-solid fa-face-sad-cry fa-bounce";
			break;
		case 2:
			color = "#CDABEB";
			icon = "fa-solid fa-face-laugh-squint fa-beat";
			break;
		case 3:
			color = "#F6C2F3";
			icon = "fa-solid fa-face-grin-tongue-wink fa-shake";
			break;
		case 4:
			color = "#F09EA7";
			icon = "fa-solid fa-face-flushed fa-beat";
			break;
		case 5:
			color = "#F6CA94";
			icon = "fa-solid fa-face-grin-stars fa-spin";
			break;
		case 6:
			color = "#FAFABE";
			icon = "fa-solid fa-face-surprise fa-spin-pulse";
			break;
		case 0:
			color = "#C1EBC0";
			icon = "fa-solid fa-face-laugh-squint fa-bounce";
			break;
		default:
			System.out.println("Invalid day");
		}

		int discount = (int) (promo.getDiscount() * 100);

		if (promo.getNoOfBooks() > 0) {
	%>
	
	<style>
	
	.active.carousel-item-start { 
	display: none !important;
	
	}
	
	.active.carousel-item-ends { 
	display: none !important;
	
	}
	
	 </style>
	
	
	

	<div class="banner m-5 mr-5 d-flex justify-content-evenly"
		style="visibility: <%=visible%>;">
		
		<div>
			<div style="background: <%=color%>;"
				class="p-4 m-1 text-align-center">
				<i class="<%=icon%> text-align-center display-6"></i>
				<p class="flex"><%=promo.getPromoName()%></p>
				<hr>
				<h1 class="display-2"><%=discount%>% OFF
				</h1>
				<hr>
			</div>

		</div>


		<div id="carouselExampleDark" class="carousel carousel-dark slide"
			data-bs-ride="carousel">

			<div class="carousel-inner">

				<!-- Each bootstrap Item apparently needs a caption and an item in order to work -->
				<!-- EG: -->
				<div class="carousel-item active p-5" data-bs-interval="2000"
					style="width: 550px; height: 350px; margin-right: 300px">

					<div>
						<img alt="Discounted Book"
							src="<%=(request.getContextPath() + "/images/promo.png")%>"
							style="width: 250px; height: 295px; position: absolute; margin-left: 14px;; z-index: 1">

						<h3 class="d-flex justify-content-end">
							Enjoy
							<%=promo.getNoOfBooks()%>
							Books @
						</h3>
						<div class="bg-light d-flex w-10 text-dark"
							style="width: 450px; height: 200px; position: absolute; margin-top: 30px; display: flex; align-items: flex-end; flex-direction: column;">
							<h1 class="display-1 text-warning"><%=discount%>%<br>
								OFF
							</h1>
						</div>
						<div class="carousel-caption d-none d-md-block">
							<h5></h5>
							<p></p>
						</div>
					</div>
				</div>


				<%
				for (Book book : discountedBooks) {
					Integer rating = book.getRating();
					Float price = book.getPrice();
					Float discount_price = book.getDiscount_price();
				%>
	

				<div class="carousel-item p-5" data-bs-interval="2000"
					style="width: 550px; height: 350px; margin-right: 250px;">
					
					<div style="">
						<img alt="Discounted Book"
							src="<%=request.getContextPath() + book.getImage()%>"
							style="box-shadow: 5px 5px; width: 200px; height: 250px; position: absolute; margin-left: 14px;; z-index: 1">

						<div class="bg-primary d-flex w-10 text-light text-left"
							style="padding: 20px; width: 450px; height: 200px; position: absolute; margin-top: 30px; display: flex; align-items: flex-end; flex-direction: column;">
							<h4>
								Title:
								<%=book.getTitle()%></h4>
							<h6 class="card-subtitle mb-2 text-muted">
								<%
								if (rating != 0) {
									for (int i = 0; i < rating; i++) {
								%>
								<i class="bi bi-star-fill text-warning"></i>
								<%
								}
								%>
								<%
								for (int i = rating; i < 10; i++) {
								%> 
								<i class="bi bi-star text-muted"></i>
								<%
								}
								} else { 
								%>
								No Rating :(
								<%
								}
								%>
							</h6>
							<div class="d-flex">
								<s><span class="text-secondary discounted-price">$<%=price%></span>
								</s>
								<h5 class="text-warning">
									$<%=decforr.format(discount_price)%></h5>
							</div>


							<form action="pages/book.jsp">
								<input type="hidden" name="book_id"
									value="<%=book.getBook_id()%>" />
								<button type="submit" class="btn btn-light mt-3">
									View Book <i class="bi bi-arrow-right-circle-fill"></i>
								</button>
							</form>
						</div>
						<div class="carousel-caption d-none d-md-block">
							<h5></h5>
							<p></p>
						</div>
					</div>
				</div>

				<%
				}
				%>

			</div>
			<button class="carousel-control-prev" type="button"
				data-bs-target="#carouselExampleDark" data-bs-slide="prev">
				<span class="carousel-control-prev-icon" aria-hidden="true"></span>
				<span class="visually-hidden">Previous</span>
			</button>
			<button class="carousel-control-next" type="button"
				style="margin-right: 200px" data-bs-target="#carouselExampleDark"
				data-bs-slide="next">
				<span class="carousel-control-next-icon" aria-hidden="true"></span>
				<span class="visually-hidden">Next</span>
			</button>
		</div>
	</div>
	<%
	} else {
	%>

	<div class="banner m-5 mr-5 ">
		<div class="d-flex justify-content-evenly ">
			<img alt=""
				src="<%=(request.getContextPath() + "/images/noPromo.png")%>"
				style="width: 550px; height: 350px;">
			<h3>No Seasonal Special for now...</h3>
		</div>
	</div>

	<%
	}
	%>

	<%
	} catch (NullPointerException e) {
	visible = "hidden";
	}
	%>

