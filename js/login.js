const signUpButton = document.getElementById("signUp");
const signInButton = document.getElementById("signIn");
const container = document.getElementById("container");

signUpButton.addEventListener("click", () => {
  container.classList.add("right-panel-active");
});

signInButton.addEventListener("click", () => {
  container.classList.remove("right-panel-active");
});

function signup() { // 회원 가입
  var newMember = new Member(document.getElementById("signupName").value,
    document.getElementById("signupEmail").value,
    document.getElementById("signupPassword").value);
  
  localStorage.setItem(newMember.email, JSON.stringify(newMember));
  console.log("signup succeeded")
}

function signin() { // 로그인
  let emailInput = document.getElementById("signinEmail");
  let passwordInput = document.getElementById("signinPassword");

  
  if (!emailInput) {  
    alert("이메일 입력 없음");
    return;
  } else if (!passwordInput) {
    alert("비밀번호 입력 없음");
    return;
  }

  let email = emailInput.value;
  let password = passwordInput.value;

  console.log(email);
  console.log(password);

  findMemberData = localStorage.getItem(email);
  if (findMemberData == null) {
    alert("존재하지 않는 이메일");
    location.reload();
  }

  findMember = JSON.parse(findMemberData);
  if (password === findMember.password) {
    console.log("로그인 성공");
    localStorage.setItem("login", email);
    location.href = "index.html";
    // window.location.replace("http://127.0.0.1:5500/index.html");
    console.log("redirect");
    // location.reload();
    alert("로그인 완료");
  }
}

class Member{
  constructor(name, email, password) {
    this.name = name;
    this.email = email;
    this.password = password;
  }
}