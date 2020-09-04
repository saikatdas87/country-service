import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DashboardComponent } from './dashboard/dashboard.component';
import { CountryDetailsComponent } from './country-details/country-details.component';
import {MatDialogModule} from "@angular/material/dialog";
import {MatButtonModule} from "@angular/material/button";
import {SharedModule} from "../shared/shared.module";



@NgModule({
  declarations: [DashboardComponent, CountryDetailsComponent],
  imports: [
    CommonModule,
    MatDialogModule,
    MatButtonModule,
    SharedModule
  ]
})
export class CountryModule { }
