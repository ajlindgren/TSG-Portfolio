$(document).ready(function () {

    loadContacts();

    $('#edit-button').click(function (event) {
        var contactId = $('#edit-contact-id').val();
        
        var haveValidationErrors = checkAndDisplayValidationErrors($('#edit-form').find('input'));

        if (haveValidationErrors) {
            return false;
        }

        $.ajax({
            type: 'PUT',
            url: 'http://localHost:8080/contact/' + contactId,
            data: JSON.stringify({
                contactId: $('#edit-contact-id').val(),
                firstName: $('#edit-first-name').val(),
                lastName: $('#edit-last-name').val(),
                company: $('#edit-company').val(),
                phone: $('#edit-phone').val(),
                email: $('#add-email').val()
            }),
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'datatype': 'json',
            success: function () {
                $('#errorMessages').empty();
                hideEditForm();
                loadContacts();
            },
            error: function () {
                $('#errorMessages').append($('<li>')
                        .attr({class: 'list-group-item list-group-item-danger'})
                        .text('Error calling web service. Try again later.'));
            }
        })
    });

    $('#add-button').click(function () {

        var haveValidationErrors = checkAndDisplayValidationErrors($('#add-form').find('input'));

        if (haveValidationErrors) {
            return false;
        }
        $.ajax({
            type: 'POST',
            url: 'http://localhost:8080/contact',
            data: JSON.stringify({
                firstName: $('#add-first-name').val(),
                lastName: $('#add-last-name').val(),
                company: $('#add-company').val(),
                phone: $('#add-phone').val(),
                email: $('#add-email').val()
            }),
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'datatype': 'json',
            success: function (contact) {
                $('#errorMessages').empty();
                $('#add-first-name').val('');
                $('#add-last-name').val('');
                $('#add-company').val('');
                $('#add-email').val('');
                $('#add-phone').val('');

                loadContacts();
            },
            error: function () {
                $('#errorMessages').append($('<li>')
                        .attr({class: 'list-group-item list-group-item-danger'})
                        .text('Error calling web service. Try again later.'));
            }
        })

    });

})

function loadContacts() {
    clearContactTable();
    var contentRows = $('#contentRows');

    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/contacts',
        success: function (contactArray) {

            $.each(contactArray, function (index, contact) {
                var name = contact.firstName + ' ' + contact.lastName;
                var company = contact.company;

                var row = '<tr>';
                row += '<td>' + name + '</td>';
                row += '<td>' + company + '</td>';
                row += '<td><a onclick="displayEditForm(' + contact.contactId + ')">Edit</a></td>';
                row += '<td><a onclick="deleteContact(' + contact.contactId + ')">Delete</a></td>';
                row += '</tr>';

                contentRows.append(row);
            });
        },
        error: function () {
            $('#errorMessages').append($('<li>')
                    .attr({class: 'list-group-item list-group-item-danger'})
                    .text('Error calling web service. Try again later.'));
        }
    });
}

function clearContactTable() {
    $('#contentRows').empty();
}

function displayEditForm(contactId) {
    $('#errorMessages').empty();

    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/contact/' + contactId,
        success: function (data, status) {
            $('#edit-contact-id').val(data.contactId);
            $('#edit-first-name').val(data.firstName);
            $('#edit-last-name').val(data.lastName);
            $('#edit-company').val(data.company);
            $('#edit-email').val(data.email);
            $('#edit-phone').val(data.phone);
        },
        error: function () {
            $('#errorMessages').append($('<li>')
                    .attr({class: 'list-group-item list-group-item-danger'})
                    .text('Error calling web service. Try again later.'));
        }
    });

    $('#contactTableDiv').hide();
    $('#editFormDiv').show();
}

function hideEditForm() {
    $('#errorMessages').empty();
    $('#edit-first-name').val('');
    $('#edit-last-name').val('');
    $('#edit-company').val('');
    $('#edit-email').val('');
    $('#edit-phone').val('');
    $('#editFormDiv').hide();
    $('#contactTableDiv').show();
}

function deleteContact(contactId) {
    $.ajax({
        type: 'DELETE',
        url: 'http://localhost:8080/contact/' + contactId,
        success: function () {
            $('#errorMessages').empty();
            loadContacts();
        },
        error: function () {
            $('#errorMessages').append($('<li>')
                    .attr({class: 'list-group-item list-group-item-danger'})
                    .text('Error calling web service. Try again later.'));
        }
    });
}

function checkAndDisplayValidationErrors(input) {
    $('#errorMessages').empty();

    var errorMessages = [];

    input.each(function () {
        if (!this.validity.valid) {
            var errorField = $('label[for=' + this.id + ']').text();
            errorMessages.push(errorField + ' ' + this.validationMessage);
        }
    });

    if (errorMessages.length > 0) {
        $.each(errorMessages, function (index, message) {
            $('#errorMessages').append($('<li>').attr({class: 'list-group-item list-group-item-danger'}).text(message));
        });
        //yes errors
        return true;
    } else {
        //no errors
        return false;
    }
}

