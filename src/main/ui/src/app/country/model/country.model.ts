export class Country {
  readonly name: String;
  readonly countryCode: String;

  constructor(name: String, countryCode: String) {
    this.name = name;
    this.countryCode = countryCode;
  }
}

export class CountryDetails extends Country {
  readonly capital: String;
  readonly population: number;
  readonly flagFileUrl: String;

  constructor(name: String, countryCode: String, capital: String, population: number, flagFileUrl: String) {
    super(name, countryCode);
    this.capital = capital;
    this.population = population;
    this.flagFileUrl = flagFileUrl;
  }
}
