/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function handleLogin(facesmessage) {
    facesmessage.severity = 'info';
    //                    doCall();
    PF('loginNotification').show([facesmessage]);


}
