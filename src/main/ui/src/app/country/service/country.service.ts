import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Country, CountryDetails} from "../model/country.model";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class CountryService {
  private host = 'http://localhost:8080';

  constructor(private http: HttpClient) {
  }

  public retrieveAllCountries(): Observable<Country[]> {
    return this.http.get<Country[]>(`${this.host}/countries`);
  }

  public getCountryDetails(countryName: String): Observable<CountryDetails> {
    return this.http.get<CountryDetails>(`${this.host}/countries/${countryName}`);
  }
}
