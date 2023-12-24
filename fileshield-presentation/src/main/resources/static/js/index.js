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
        console.log(response)
        if (response) {
          console.log('Custom extension added successfully.');
          addCustomExtension();
        } else {
          alert("이미 존재하는 확장자입니다.");
        }
      },
      error: function () {
        console.error('Error in Ajax request.');
      }
    });
  });
});

function addCustomExtension() {
  const customExtension = $('#customExtension').val();
  if (customExtension.trim() !== "") {
    $('#customExtensionsList').append(
        '<div class="badge badge-secondary mr-2">' +
        customExtension +
        '<button type="button" class="close" onclick="removeCustomExtension(this)" aria-label="Close">'
        +
        '<span aria-hidden="true">&times;</span>' +
        '</button>' +
        '</div>');
    $('#customExtension').val('');
  }
}

function removeCustomExtension(element) {
  $(element).parent().remove();
}
