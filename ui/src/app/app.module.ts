import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatSliderModule, MatTableModule} from '@angular/material';
import {BudgetComponent} from './budget/budget.component';
import {HttpClientModule} from '@angular/common/http';
import {PostComponent} from './post/post.component';

@NgModule({
  declarations: [
    AppComponent,
    BudgetComponent,
    PostComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    HttpClientModule,
    MatSliderModule,
    MatTableModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
