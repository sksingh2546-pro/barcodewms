function getTotalStockManagementData() {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            console.log(this.responseText);


            var result = JSON.parse(this.responseText);
            if(result.product.length==0){
            Toastify({
                            text: "Data Not Found !",
                            duration: 3000,
                            gravity: "top",
                            position: 'right',
                            backgroundColor: "red",
                            close: true
                        }).showToast();
            }

            else{

            for (var i = 0; i < result.product.length; i++) {

                document.getElementById("todayStockTable").innerHTML +=
                    "<tr>" +
                    '<td>' + (i + 1) + '</td>' +
                    '<td>' + result.product[i].name_of_item + '</td>' +
                    '<td>' + result.product[i].num_pcs + '</td>' +
                    '<td>' + result.product[i].per_pcs_weight + '</td>' +
                    '<td>' + result.product[i].packaging + '</td>' +
                    '<td>' + result.product[i].carton_gross_weight + '</td>' +
                    '<td>' + result.product[i].hsn + '</td>' +
                    '</tr>';
            }
             Toastify({
                                                        text: "Data Fetched Successfully",
                                                        duration: 3000,
                                                        gravity: "top",
                                                        position: 'right',
                                                        backgroundColor: "#01cf68",
                                                        close: true
                                                    }).showToast();
        }
        }
    };
    xhttp.open("GET", "/api/getAllData?user_name="+localStorage.getItem("user_name"), true);
    xhttp.send();
}
