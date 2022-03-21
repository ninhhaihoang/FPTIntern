// $(document).ready(function(){
//     $('[data-quantity="plus"]').on("click",function(e){
//         e.preventDefault();
//         qtyInput = $("#quantity");
//         newQty = parseInt(qtyInput.val()) + 1;
//         qtyInput.val(newQty);
//         console.log(qtyInput.val());
//     });
//     $('[data-quantity="minus"]').on("click",function(e){
//         e.preventDefault();
//         qtyInput = $("#quantity");
//         newQty = parseInt(qtyInput.val()) - 1;
//         if (newQty > 0) qtyInput.val(newQty);
//         console.log(qtyInput.val());
//     });
// });
window.onload = function () {
    let minusBtn = document.getElementById("minusBtn");
    let plusBtn = document.getElementById("plusBtn");
    let qtyInput = document.getElementById("quantity");
    let qty = 1;
    qtyInput.innerText = qty;

    minusBtn.onclick = function() {
        if (qty > 0) {
            qty = qty-1;
            qtyInput.innerText = qty;
        }
    }
    plusBtn.onclick = function() {
        qty = qty+1;
        qtyInput.innerText = qty;
    }
}
