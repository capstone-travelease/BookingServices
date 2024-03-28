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
function test() {
var array = [
          { roomName: "Tiêu chuẩn", quanity: 10, price: 10000 },
          { roomName: "VIP", quanity: 6, price: 10000 },
          { roomName: "Luxury", quanity: 8, price: 50020 },
        ];
        var result = ``;
        for (let i = 0; i < array.length; i++) {
result += `
<tr>
<td>${array[i].roomName}</td>
                    <td>${array[i].quanity}</td>
                    <td>0VND</td>
                    <td>${array[i].price}VND</td>
                </tr>
                `;
        }
        $(".list_room").append(result);
      }
    </script>
  </head>
  <body>
    <main>
      <div class="container">
        <h2 class="header">Thank you for your order</h2>
        <div class="content">
          <div style="margin: 5px 0px">Hi 123!</div>
          <div style="margin: 5px 0px">
            Thank you for using our services! We will respond to you shortly
            upon receiving your hotel booking information
          </div>
          <h4 class="content_order_id">[Purchase #886] (27 December, 2023)</h4>
          <h2 class="content_hotel_name">Thuy Van Hotel</h2>
          <div class="order_infomation">
            Address: SGT Miranda McAnderson 6543 N 9th Street APO, AA 33608-1234
          </div>
          <div style="display: flex; justify-content: space-between">
            <div>
              <div class="order_infomation">Check-in Date: 25/12/2023</div>
              <div class="order_infomation">Check-out Date: 26/12/2023</div>
            </div>
            <div>
              <div class="order_infomation">Status: Pending</div>
            </div>
          </div>
          <div class="order_infomation">Note: Done</div>
          <table>
            <tr>
              <th>Room ID</th>
              <th>Quantity</th>
              <th>Coupon</th>
              <th>Price</th>
            </tr>
            <tbody class="list_room">
              <script>
                test();
              </script>
            </tbody>
            <tr>
              <td>Total:</td>
              <td></td>
              <td></td>
              <td>3500000VND</td>
            </tr>
          </table>
          <h3 class="content_order_id">Payment Infomation</h3>
          <div>Payment method: Momo</div>
          <div>Email: nguyen@gmail.com</div>
          <div class="content_footer">
            Chúng tôi đang tiến hành hoàn thiện đơn đặt hàng của bạn
          </div>
        </div>
      </div>
    </main>
  </body>
</html>
