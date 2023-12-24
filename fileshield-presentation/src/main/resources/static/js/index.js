/**
 * 체크 박스를 클릭하면 해당 확장자의 차단, 차단 해제 요청을 서버에 전송합니다.
 */
$(document).ready(function () {
  $('.extension-checkbox').change(function () {
    const checkbox = $(this);
    const extensionId = $(this).data('extension-id');
    const isBlocked = this.checked;
    const apiUrl = `http://localhost:8080/api/v1/file-extensions/${extensionId}/${isBlocked
        ? 'fixed-block' : 'fixed-unblock'}`;

    $.ajax({
      url: apiUrl,
      type: 'PATCH',
      success: function (response) {
        if (response) {
          console.log('Extension block status toggled successfully.');
        } else {
          console.error('Failed to toggle extension block status.');
          checkbox.prop('checked', !isBlocked);
        }
      },
      error: function () {
        console.error('Error in Ajax request.');
        checkbox.prop('checked', !isBlocked);
      }
    });
  });
});

/**
 * Add 버튼을 클릭하면 서버에 커스텀 확장자 차단 요청을 전송합니다.
 */
$(document).ready(function () {
  $('.btn-outline-secondary').click(function () {
    const customExtensionInput = $('#customExtension');
    const customExtension = customExtensionInput.val().trim();

    if (customExtension === '') {
      alert("추가할 확장자를 입력해주세요.")
      return;
    }

    $.ajax({
      url: 'http://localhost:8080/api/v1/file-extensions/custom-block',
      type: 'POST',
      dataType: 'json',
      contentType: 'application/json',
      async: false,
      data: JSON.stringify({extension: customExtension}),
      success: function (response) {
        if (response) {
          console.log('Custom extension added successfully.');
          addCustomExtension(response.data.extensionId);
        } else {
          console.log('Failed to add custom extension.');
        }
      },
      error: function (xhr, textStatus, errorThrown) {
        if (xhr.status === 400 && xhr.responseJSON
            && xhr.responseJSON.message) {
          console.log(
              'Bad Request: Custom extension cannot be added due to bad request.');
          alert(xhr.responseJSON.message);
          return;
        }
        if (xhr.status === 409) {
          console.log(
              'Conflict: Custom extension cannot be deleted due to conflict.');
          alert('이미 존재하는 확장자입니다.');
          return;
        }
        console.error('Error in Ajax request:', textStatus, errorThrown);
      }
    });
  });
});

function addCustomExtension(extensionId) {
  const customExtension = $('#customExtension').val();
  if (customExtension.trim() !== "") {
    $('#customExtensionsList').append(
        `<div class="badge badge-secondary mr-2" data-custom-extension-id="${extensionId}">
          <span>${customExtension}</span>
          <button type="button" class="close" aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>`
    );
    $('#customExtension').val('');
  }
}

/**
 * x 버튼을 클릭하면 서버에 커스텀 확장자 차단 해제 요청을 전송합니다.
 */
$('#customExtensionsList').on('click', '.close', function () {
  const badge = $(this).closest('.badge');
  const extensionId = badge.data('custom-extension-id');

  $.ajax({
    url: `http://localhost:8080/api/v1/file-extensions/${extensionId}/custom-unblock`,
    type: 'DELETE',
    dataType: 'json',
    contentType: 'application/json',
    async: false,
    success: function (response) {
      if (response) {
        console.log('Custom extension deleted successfully.');
        removeCustomExtension(badge);
        return;
      }
      console.log('Failed to delete custom extension.');
    },
    error: function () {
      console.error('Error in Ajax request.');
    }
  });
});

function removeCustomExtension(element) {
  $(element).remove();
}
