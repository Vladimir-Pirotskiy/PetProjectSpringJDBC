import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {PostResponse} from "../models";

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.scss']
})
export class PostComponent implements OnInit {
  posts: PostResponse[];

  constructor(private http:HttpClient) { }

  ngOnInit() {
    this.http.get('api/posts/Tom').subscribe((res: PostResponse[]) => this.posts = res);
  }

}
