function getTotalStockManagementData() {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            console.log(this.responseText);


            var result = JSON.parse(this.responseText);
            if(result.sum.length==0){
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

            for (var i = 0; i < result.sum.length; i++) {
                 Toastify({
                                            text: "Data Fetched Successfully",
                                            duration: 3000,
                                            gravity: "top",
                                            position: 'right',
                                            backgroundColor: "#01cf68",
                                            close: true
                                        }).showToast();
                document.getElementById("todayStockTable").innerHTML +=
                    "<tr>" +
                    '<td>' + (i + 1) + '</td>' +
                    '<td>' + result.sum[i].name_of_item + '</td>' +
                    '<td>' + result.sum[i].no_of_pcs + '</td>' +
                    '<td>' + result.sum[i].per_pcs_weight + '</td>' +
                    '<td>' + result.sum[i].packaging + '</td>' +
                    '<td>' + result.sum[i].carton_gross_weight + '</td>' +
                    '<td>' + result.sum[i].hsn + '</td>' +
                    '<td>' + result.sum[i].qty + '</td>' +
                    '</tr>';
            }
        }
        }
    };
    xhttp.open("GET", "/api/getNameOfItem?user_name="+localStorage.getItem("user_name"), true);
    xhttp.send();
}
