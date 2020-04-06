import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {TransactionsDTO} from '../models';

@Component({
  selector: 'app-budget',
  templateUrl: './budget.component.html',
  styleUrls: ['./budget.component.scss']
})
export class BudgetComponent implements OnInit {

  transactions: TransactionsDTO[];
  columns: string[] = [ 'id', 'amount', 'date', 'familyMember', 'income', 'outcome' ];

  constructor(private http: HttpClient) { }

  ngOnInit() {
    this.http.get('/api/transactions').subscribe((data: TransactionsDTO[]) => this.transactions = data);
  }

}
