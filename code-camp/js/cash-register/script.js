let price = 19.5;
let cid = [
  ["PENNY", 1.01],
  ["NICKEL", 2.05],
  ["DIME", 3.1],
  ["QUARTER", 4.25],
  ["ONE", 90],
  ["FIVE", 55],
  ["TEN", 20],
  ["TWENTY", 60],
  ["ONE HUNDRED", 100],
];

cid = [
  ["PENNY", 0.5],
  ["NICKEL", 0],
  ["DIME", 0],
  ["QUARTER", 0],
  ["ONE", 0],
  ["FIVE", 0],
  ["TEN", 0],
  ["TWENTY", 0],
  ["ONE HUNDRED", 0],
];

const dict = {
  PENNY: 0.01,
  NICKEL: 0.05,
  DIME: 0.1,
  QUARTER: 0.25,
  ONE: 1,
  FIVE: 5,
  TEN: 10,
  TWENTY: 20,
  "ONE HUNDRED": 100,
};

const cash = document.getElementById("cash");
const purchaseBtn = document.getElementById("purchase-btn");
const changeDue = document.getElementById("change-due");

const checkCashRegister = () => {
  const priceCents = Math.round(price * 100);
  const cashValueCents = Math.round(Number(cash.value) * 100);

  if (cashValueCents < priceCents) {
    alert("Customer does not have enough money to purchase the item");
    return;
  }
  if (cashValueCents === priceCents) {
    alert("No change due - customer paid with exact cash");
    changeDue.textContent = "No change due - customer paid with exact cash";
    return;
  }

  const result = calculateChange(priceCents, cashValueCents);
  changeDue.textContent = formatResult(result);
};

const calculateChange = (price, cash) => {
  const sorted = cid.sort((a, b) => dict[b[0]] - dict[a[0]]);
  let funds = getFunds();

  let result = checkFunds(price, funds, cash);
  if (result.Status === "INSUFFICIENT_FUNDS") {
    return result;
  }

  let remain = cash - price;
  for (const item of sorted) {
    const [name, value] = item;
    const valueCents = Math.round(value * 100);
    const rateCents = Math.round(dict[name] * 100);

    if (remain - valueCents >= 0) {
      remain -= valueCents;
      funds -= valueCents;
      result[name] = value;
      continue;
    }

    if (remain - rateCents >= 0) {
      const amount = Math.floor(remain / rateCents) * rateCents;
      remain -= amount;
      funds -= amount;
      result[name] = amount / 100;
      continue;
    }

    if (remain === 0) {
      break;
    }
  }

  if (remain > 0) {
    result = { Status: "INSUFFICIENT_FUNDS" };
  }

  if (funds === 0) {
    result.Status = "CLOSED";
  }

  return result;
};

const getFunds = () => {
  let funds = 0;
  cid.forEach((item) => {
    funds += Math.round(item[1] * 100);
  });
  return funds;
};

const checkFunds = (price, funds, cash) => {
  if (cash - price > funds) {
    return { Status: "INSUFFICIENT_FUNDS" };
  }
  return { Status: "OPEN" };
};

const formatResult = (result) => {
  let formattedResult = "";
  for (const [key, value] of Object.entries(result)) {
    if (key === "Status") {
      formattedResult += `${key}: ${value} `;
    } else if (value !== 0) {
      formattedResult += ` ${key}: $${value}`;
    }
  }
  return formattedResult;
};

purchaseBtn.addEventListener("click", checkCashRegister);
