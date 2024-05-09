window.onload = function () {
  current = localStorage.getItem("login");
  // if (!current) {
  //   alert("로그인하시오.");
  //   window.location.replace("index.html");
  // }

  console.log(current);
  
  userData = localStorage.getItem(current);
  user = JSON.parse(userData);
  document.getElementById("user-name-title").innerHTML = user.name;
  document.getElementById("fullName").placeholder = user.name;
  document.getElementById("eMail").placeholder = user.email;
}

update = function () {
  updateNameData = document.getElementById("fullName");
  updateEmailData = document.getElementById("eMail");
  
  if (!updateNameData || !updateEmailData) {
    alert("입력값 확인");
    return;
  }

  updateEmail = updateEmailData.value;
  updateName = updateNameData.value;
  
  userEmail = localStorage.getItem("longin");
  localStorage.removeItem("login");
  
  localStorage.removeItem(userEmail);

  newMember = new Member(updateName, updateEmail, user.password);
  localStorage.setItem(updateEmail, JSON.stringify(newMember));
  console.log(updateEmail);

  alert("다시 로그인하시오");
  window.location.replace("index.html");
}

class Member {
  constructor(name, email, password) {
    this.name = name;
    this.email = email;
    this.password = password;
  }
}

deleteOne = function () {

  userEmail = localStorage.getItem("login");
  localStorage.removeItem("login");
  
  localStorage.removeItem(userEmail);

  alert("탈퇴되었음");
  window.location.replace("index.html");
}
