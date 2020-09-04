import { Component, OnInit } from '@angular/core';
import {CountryService} from "../service/country.service";
import {TableColumn} from "../../shared/the-table/the-table.component";
import {element} from "protractor";
import {Country} from "../model/country.model";

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.less']
})
export class DashboardComponent implements OnInit {

  countryColumns: TableColumn[] = [
    new TableColumn('name', 'NAME', (element: Country) => `${element.name}`),
    new TableColumn('countryCode', 'COUNTRY CODE', (element: Country) => `${element.countryCode}`),
    new TableColumn('action', 'ACTION', (_: any) => 'Details', (_: any) => 'Details')
  ];
  countriesTableData : Country[];

  constructor(private countryService : CountryService) { }

  ngOnInit() {
    this.countryService.retrieveAllCountries().subscribe(countries => {
      console.log('Co', countries.length)
      this.countriesTableData = countries;
    }, error => {

    });
  }

}
