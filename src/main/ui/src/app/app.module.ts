import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatButtonModule} from "@angular/material/button";
import {CountryDetailsComponent} from "./country/country-details/country-details.component";
import {CountryModule} from "./country/country.module";
import {HttpClientModule} from "@angular/common/http";

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatButtonModule,
    CountryModule,
    HttpClientModule
  ],
  providers: [],
  entryComponents: [CountryDetailsComponent],
  bootstrap: [AppComponent]
})
export class AppModule { }
