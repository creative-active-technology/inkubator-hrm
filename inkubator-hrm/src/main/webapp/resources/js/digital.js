/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function() {
//    $.session.set('bahasa','1');
//    alert("waktu " + $.session.get("bahasa"));
    var monthNames = ["Januari", "Februari", "Maret", "April", "Mei", "Juni", "Juli", "Agustus", "September", "Oktober", "November", "Desember"];
    var dayNames = ['Minggu', 'Senin', 'Selasa', 'Rabu', 'Kamis', 'Jum"at', 'Sabtu'];
// Create a newDate() object
    var newDate = new Date();
// Extract the current date from Date object
    newDate.setDate(newDate.getDate());
// Output the day, date, month and year   
    $('#Date').html(dayNames[newDate.getDay()] + ", " + newDate.getDate() + ' ' + monthNames[newDate.getMonth()] + ' ' + newDate.getFullYear());

    setInterval(function() {
        // Create a newDate() object and extract the seconds of the current time on the visitor's
        var seconds = new Date().getSeconds();
        // Add a leading zero to seconds value
        $("#sec").html((seconds < 10 ? "0" : "") + seconds);
    }, 1000);

    setInterval(function() {
        // Create a newDate() object and extract the minutes of the current time on the visitor's
        var minutes = new Date().getMinutes();
        // Add a leading zero to the minutes value
        $("#min").html((minutes < 10 ? "0" : "") + minutes);
    }, 1000);

    setInterval(function() {
        // Create a newDate() object and extract the hours of the current time on the visitor's
        var hours = new Date().getHours();
        // Add a leading zero to the hours value
        $("#hours").html((hours < 10 ? "0" : "") + hours);
    }, 1000);
});
