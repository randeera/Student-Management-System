const saveStudent = () => {
    let name = $('name-input').val();
    let address = $('address-input').val();
    let mobile = $('mobile-input').val();

    $.ajax({
        method:"POST",
        contentType:"application/json",
        url:"http://localhost:8080/api/v1/students",
        data:JSON.stringify({
            "studentId": "",
            "studentName": name,
            "studentAddress": address,
            "mobileNumber": mobile
        }),
        success: function (data){
            alert('saved !');
        },
        error: function (xhr, exception){
            alert('Error !');
        }
    })
}
