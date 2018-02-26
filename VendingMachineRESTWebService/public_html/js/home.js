var totalDeposit = 0;
var currentProduct = null;

$(document).ready(function () {

    $('body').css({
        'background-image': 'url(img/vendingMachine.jpg)',
        'background-size': '100% 150%',
        'background-repeat': 'no-repeat'
    });
    
    $('h1').css({
        'font-family': 'Shrikhand, cursive',
        'color': 'white'
    });
    
    $('h3').css({
        'font-family': 'Montserrat, sans-serif',
        'font-weight': 'bold'
    });

    $('input').css({
        'max-width': '200px'
    });

    $('#right').children().css({
        'max-width': '225px',
        'border': '1px solid black',
        'border-radius': '5px',
        'margin-right': '10px'
    });

    $('#right').css({
        'padding-right': '50px',
        'padding-top': '7px',
        'min-width': '250px'
    })

    $('#left').css({
        'padding-left': '100px',
        'padding-top': '7px'
    });

    var purchaseSound = new Audio("sound/purchaseSound.mp3");
    var changeSound = new Audio("sound/changeSound.mp3");
    var coinSound = new Audio("sound/coinSound.mp3");
    var dollarSound = new Audio("sound/dollarSound.mp3");
    var errorSound = new Audio("sound/errorSound.mp3");

    loadProducts();

    $('#add-dollar, #add-quarter, #add-dime, #add-nickel').click(function () {
        if (this.id == 'add-dollar') {
            totalDeposit += 1;
            dollarSound.playbackRate = 1.1;
            dollarSound.volume = .4;
            dollarSound.play();
            dollarSound.currentTime = 0;
        } else if (this.id == 'add-quarter') {
            totalDeposit += .25;
            coinSound.playbackRate = .5;
            coinSound.play();
            coinSound.currentTime = 0;
        } else if (this.id == 'add-dime') {
            totalDeposit += .10;
            coinSound.playbackRate = 1;
            coinSound.play();
            coinSound.currentTime = 0;
        } else if (this.id == 'add-nickel') {
            totalDeposit += .05;
            coinSound.playbackRate = 1.5;
            coinSound.play();
            coinSound.currentTime = 0;
        }

        $('#disabledTotalInput').val('$' + totalDeposit.toFixed(2));
        $('#display-change').val('');
    });

    $('#return-change').click(function () {
        var quarters = Math.floor(totalDeposit / .25);
        var remainingChange = totalDeposit % .25;
        var dimes = Math.floor(remainingChange / .10);
        var remainingChange2 = remainingChange % .10;
        var nickels = Math.floor(remainingChange2 / .05);

        $('#display-change').val(quarters + ' Q(s), '
                + dimes + ' D(s), and '
                + nickels + ' N(s)');

        if (totalDeposit !== 0)
            changeSound.play();
        changeSound.currentTime = 0;
        totalDeposit = 0;
        $('#disabledTotalInput').val('$' + totalDeposit.toFixed(2));
        currentProduct = null;
        $('#display-item').val('');

    });


    $('#make-purchase').click(function () {
        if (currentProduct !== null) {
            $.ajax({
                type: 'GET',
                url: 'http://localhost:8080/money/'
                        + totalDeposit.toFixed(2) + '/item/'
                        + currentProduct.find('.id').text(),
                success: function (change) {
                    $('#display-change').val(change.quarters + ' Q(s), '
                            + change.dimes + ' D(s), and '
                            + change.nickels + ' N(s)');
                    totalDeposit = 0;
                    $('#disabledTotalInput').val('$' + totalDeposit.toFixed(2));
                    currentProduct = null;
                    $('#display-item').val('');
                    $('#disable-messages').val('Thank You!');
                    loadProducts();
                    purchaseSound.play();
                    purchaseSound.currentTime = 0;
                },
                error: function (xhr, statusCode) {
                    $('#disable-messages').val(xhr.responseJSON.message);
                    errorSound.play();
                    errorSound.currentTime = 0;
                }
            })
        }
    });
    


});

function loadProducts() {
    var productDiv = $('#productDiv');
    productDiv.html('');

    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/items',
        success: function (productArray) {

            $.each(productArray, function (index, product) {
                var productSquare = '<div class="productClass col-md-3 id="tile' + product.id + '" style="border: 1px solid black">';
                
                productSquare += '<div class="front">';
                productSquare += '<img src="img/' + product.id + '.jpg">';
                productSquare += '</div>';
                
                productSquare += '<div class="back">';
                productSquare += '<p class="pull-left id" style="font-size:15px">' + product.id + '</p>';
                productSquare += '<br>';
                productSquare += '<p class="text-center name" style="font-size:35px">' + product.name + '</p>';
                productSquare += '<br><br>';
                productSquare += '<p class="text-center price" style="font-size:20px"> $' + product.price.toFixed(2) + '</p>';
                productSquare += '<br>';
                productSquare += '<p class="text-center quantity" style="font-size:20px"> Quantity Left: <span>' + product.quantity + '</span></p>';
                productSquare += '</div>';
                
                productSquare += '</div>';

                productDiv.append(productSquare);
            });

            $('.productClass').click(function () {
                $('#display-item').val('');
                $('#display-item').val($(this).find('.name').text() + ': ' + $(this).find('.price').text());
                $('#display-change').val('');
                currentProduct = $(this);
                $('#disable-messages').val('');
            });
            
            $('.productClass').flip({
                'trigger': 'hover'
            });

            $('.productClass').css({
                'border-radius': '5px',
                'margin': '2px',
                'width': '240px',
                'height': '230px',
                'overflow': 'hidden',
                'padding-left': '0px'
            });
            
            $('.back').css({
                'padding-left': '10px',
                'border-color': 'white',
                'color': 'white',
                'font-family': 'Faustina, serif'
            });

            $('img').css({
                'position': 'absolute',
                'top': '-9999px',
                'left': '-9999px',
                'right': '-9999px',
                'bottom': '-9999px',
                'margin': 'auto'
            });
        },
        error: function () {
            console.log('ERROR LOADPRODUCTS');
        }
    });
}