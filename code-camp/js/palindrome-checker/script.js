const button = document.getElementById("check-btn");
const input = document.getElementById("text-input");
const result = document.getElementById("result");

const getResult = (str) =>{
  return `${str} is ${checkPalindrome(str)?"": "not"} a palindrome`;
}

const checkPalindrome = (str) => {
 const cleanedInput = str.replace(/[^a-zA-Z0-9]/g, "").toLowerCase();
  const reversedInput = cleanedInput.split('').reverse().join('');
  return cleanedInput === reversedInput;
}

const validate = () => {
  if (input.value === "") {
    alert("Please input a value");
    return;
  } else {
    result.innerHTML = getResult(input.value);
  }
};

button.addEventListener("click",validate)

