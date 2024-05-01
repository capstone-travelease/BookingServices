<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Document</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <style>
      body {
font-family: Arial, Helvetica, sans-serif;
font-size: 16;
margin: 0;
padding: 0;
}
      main {
display: flex;
align-items: center;
justify-content: space-between;
width: 100%;
min-height: 100vh;
}
.container {
margin: 0 auto;
border: 1px solid gray;
width: 700px;
}
.header {
padding: 30px;
background-color: #fa5a60;
color: white;
}
.content {
padding: 10px 30px;
}
h1,
h2,
h3,
h4 {
margin: 0;
padding: 0;
}
.content_order_id {
color: #fa5a60;
}
.order_infomation {
padding: 5px 0px;
}
table {
border: 1px solid gray;
border-collapse: collapse;
margin: 10px 0px;
width: 100%;
}

td {
border: 1px solid gray;
padding: 10px;
}
th {
border: 1px solid gray;
padding: 10px;
}
.content_footer {
margin-top: 20px;
}
.content_hotel_name {
padding: 10px 0px;
}
</style>
<script>

</script>
  </head>
  <body>
    <main>
      <div class="container">
        <h2 class="header">Thank you for your order</h2>
        <div class="content">
          <div style="margin: 5px 0px">Hi ${fullName}</div>
          <div style="margin: 5px 0px">
            Thank you for using our services! We will respond to you shortly
            upon receiving your hotel booking information
          </div>
          <h4 class="content_order_id">[Purchase #${bookingId}] (${bookingDate})</h4>
          <h2 class="content_hotel_name">${hotelName}</h2>
          <div class="order_infomation">
            Address: ${hotelAddress}
          </div>
          <div style="display: flex; justify-content: space-between">
            <div>
              <div class="order_infomation">Check-in Date: ${checkinDate}</div>
              <div class="order_infomation">Check-out Date: ${checkoutDate}</div>
            </div>
            <div>
              <div class="order_infomation">Status: ${bookingStatus}</div>
            </div>
          </div>
          <table>
            <tr>
              <th>Room Name</th>
              <th>Quantity</th>
              <th>Price</th>
            </tr>
            <tbody class="list_room">
                  <#list list as item>
                      <tr>
                        <td>${item.roomName}</td>
                        <td>${item.roomQuantity}</td>
                        <td>${item.roomPrice}VND</td>
                    </tr>
                  </#list>
            </tbody>
            <tr>
              <td>Total:</td>
              <td></td>
              <td>${totalPrice}VND</td>
            </tr>
          </table>
          <h3 class="content_order_id">Payment Infomation</h3>
          <div>Payment method: ${nameBank}</div>
          <div>Email: ${email}</div>
          <div class="content_footer">
            We are currently completing your order
          </div>
        </div>
      </div>
    </main>
  </body>
</html>
