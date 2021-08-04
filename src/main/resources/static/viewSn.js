function tableList(){
    var xhttp = new XMLHttpRequest();
       xhttp.onreadystatechange = function () {
           if (this.readyState == 4 && this.status == 200) {
               console.log(this.responseText);


               var result = JSON.parse(this.responseText);
              document.getElementById("productTable").innerHTML="";
               if(result.out.length==0){
                       Toastify({
                           text: "Data Not Found !",
                           duration: 3000,
                           gravity: "top",
                           position: 'right',
                           backgroundColor: "Red",
                           close: true
                       }).showToast();


              }else{

               Toastify({
                   text: "Data Fetched Successfully",
                   duration: 3000,
                   gravity: "top",
                   position: 'right',
                   backgroundColor: "#01cf68",
                   close: true
               }).showToast();

               for (var i = 0; i < result.out.length; i++) {
                   document.getElementById("productTable").innerHTML +=
                       "<tr>" +
                       '<td class="text-center" style="width: 7%;">' + (i + 1) + '</td>' +
                       '<td class="text-center" style="width: 25%;">' + result.out[i].name_of_item + '</td>' +
                       '<td class="text-center" style="width: 15%;">' + result.out[i].no_of_pcs + '</td>' +
                       '<td class="text-center" style="width: 15%;">' + result.out[i].per_pcs_weight + '</td>' +
                       '<td class="text-center" style="width: 10%;">' + result.out[i].packaging + '</td>' +
                       '<td class="text-center" style="width: 10%;">' + result.out[i].carton_weight + '</td>' +
                       '<td class="text-center" style="width: 10%;">' + result.out[i].hsn + '</td>' +
                       '<td class="text-center" style="width: 10%;">' + result.out[i].sales_no + '</td>' +
                       '<td class="text-center" style="width: 10%;">' + result.out[i].qty + '</td>' +
                       '</tr>';
               }}
           }
       };
       xhttp.open("GET", "api/getOutDataForTelly?sales_no="+localStorage.getItem("sales_no")+"&user_name="+localStorage.getItem("user_name"), true);
       xhttp.send();

   }