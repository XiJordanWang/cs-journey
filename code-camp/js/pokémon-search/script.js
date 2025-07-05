const searchInput = document.getElementById("search-input");
const searchButton = document.getElementById("search-button");
const pokemonName = document.getElementById("pokemon-name");
const pokemonImg = document.getElementById("img");
const pokemonId = document.getElementById("pokemon-id");
const pokemonWeight = document.getElementById("weight");
const pokemonHeight = document.getElementById("height");
const pokemonType = document.getElementById("types");
const pokemonHp = document.getElementById("hp");
const pokemonAttack = document.getElementById("attack");
const pokemonDefense = document.getElementById("defense");
const pokemonSpecialAttack = document.getElementById("special-attack");
const pokemonSpecialDefense = document.getElementById("special-defense");
const pokemonSpeed = document.getElementById("speed");

const pokemonURL = "https://pokeapi-proxy.freecodecamp.rocks/api/pokemon/";

const fetchPokemon = async (url) => {
  try {
    const response = await fetch(url);
    const data = await response.json();
    pokemonName.textContent = data.name.toUpperCase();
    pokemonImg.innerHTML = `<img id="sprite" src="${data.sprites.front_default}" alt="${data.name}" />`;
    pokemonId.textContent = `#${data.id}`;
    pokemonWeight.textContent = `${data.weight}`;
    pokemonHeight.textContent = `${data.height}`;
    pokemonType.innerHTML = data.types
      .map(
        (obj) => `<span class="type ${obj.type.name}">${obj.type.name}</span>`
      )
      .join("");
    pokemonHp.textContent = `${data.stats[0].base_stat}`;
    pokemonAttack.textContent = `${data.stats[1].base_stat}`;
    pokemonDefense.textContent = `${data.stats[2].base_stat}`;
    pokemonSpecialAttack.textContent = `${data.stats[3].base_stat}`;
    pokemonSpecialDefense.textContent = `${data.stats[4].base_stat}`;
    pokemonSpeed.textContent = `${data.stats[5].base_stat}`;
  } catch (error) {
    alert("Pokémon not found");
  }
};

const searchPokemon = () => {
  const pokemon = searchInput.value.toLowerCase();
  const url = `${pokemonURL}${pokemon}`;
  fetchPokemon(url);
};

searchButton.addEventListener("click", searchPokemon);
