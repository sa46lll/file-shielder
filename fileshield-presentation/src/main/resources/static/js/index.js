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

$(document).ready(function () {
  $('.extension-checkbox').change(function () {
    const extensionId = $(this).data('extension-id');
    const isBlocked = this.checked;
    const apiUrl = `http://localhost:8080/api/v1/file-extensions/${extensionId}/${isBlocked ? 'fixed-block' : 'fixed-unblock'}`;

    $.ajax({
      url: apiUrl,
      type: 'PATCH',
      success: function (response) {
        if (response.code === '요청에 성공했습니다.') {
          console.log('Extension block status toggled successfully.');
        } else {
          console.error('Failed to toggle extension block status.');
        }
      },
      error: function () {
        console.error('Error in Ajax request.');
      }
    });
  });
});
