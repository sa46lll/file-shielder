function addCustomExtension() {
  const customExtension = $('#customExtension').val();
  if (customExtension.trim() !== "") {
    $('#customExtensionsList').append('<div class="badge badge-secondary mr-2">' +
        customExtension +
        '<button type="button" class="close" onclick="removeCustomExtension(this)" aria-label="Close">' +
        '<span aria-hidden="true">&times;</span>' +
        '</button>' +
        '</div>');
    $('#customExtension').val('');
  }
}

function removeCustomExtension(element) {
  $(element).parent().remove();
}
