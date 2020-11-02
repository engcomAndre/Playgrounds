import { ResponsePageable } from './../model/responsepPageable.model';
import { HttpClient,  HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class LiveService {

  apiUrl = 'http://localhost:8081/lives';

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'

    })
  };

  constructor(private httpClient: HttpClient) { }

  public getLivesWithFlag(flag: string): Observable<ResponsePageable> {
    return this.httpClient.get<ResponsePageable>(
      this.apiUrl + '?flag=' + flag
    );
  }
}