import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {BudgetComponent} from './budget/budget.component';
import {PostComponent} from "./post/post.component";


const routes: Routes = [
  { path: 'budget', component: BudgetComponent },
  { path: 'posts', component: PostComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
