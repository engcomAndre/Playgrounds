import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Live } from '../model/live.model';
import { ResponsePageable } from '../model/responsePageacle.model';

@Injectable({
  providedIn: 'root'
})
export class LiveService {

  apiUrl = 'http://localhost:8080/lives';

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  }

  constructor(
    private httpclient: HttpClient
  ) { }


  public getLivesWithFlag(flag: string): Observable<ResponsePageable> {
    return this.httpclient.get<ResponsePageable>(this.apiUrl + '?flag=' + flag);
  }

  public postLives(live: any): Observable<Live> {
    return this.httpclient.post<any>(this.apiUrl, live, this.httpOptions);
  }

}
