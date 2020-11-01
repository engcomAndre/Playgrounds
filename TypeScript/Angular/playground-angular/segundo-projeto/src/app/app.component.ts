import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  serverElements = [{
    type: 'server',
    name: 'teste name',
    content: 'content'
  }];

  onServerAdded(serverData: { serverName: string, serverContent: string }) {
    console.log("onServerAdded");
    this.serverElements.push({
      type: 'server',
      name: serverData.serverName,
      content: serverData.serverContent
    });
  }

  onBlueprintAdded(blueprintData: { blueprintName: string, blueprintContent: string }) {
    console.log("onBlueprintAdded");
    this.serverElements.push({
      type: 'blueprint',
      name: blueprintData.blueprintName,
      content: blueprintData.blueprintContent
    });
  }

  onChangeFirst(){
    this.serverElements[0].name = 'changed name';

  }

  onDestroyFirst(){
    this.serverElements.splice(0,1);
  }


}
