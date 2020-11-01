import { RouterStateSnapshot } from '@angular/router';
import { ActivatedRouteSnapshot } from '@angular/router';
import { Resolve } from '@angular/router';
import { Observable } from 'rxjs/Observable';
import { ServersService } from '../servers.service';

interface Server {
  id: number;
  name: string;
  status: string;
}

export class ServerResolver implements Resolve<{ id: number, name: string, status: string }> {
  constructor(private serverService: ServersService) { }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Server> | Promise<Server> | Server {
    return this.serverService.getServer(+route.params['id']);
  }

}
