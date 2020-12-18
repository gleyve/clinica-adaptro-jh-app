import { Injectable } from '@angular/core';
//import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';

@Injectable({
  providedIn: 'root',
})
export class MyAdaptroUtilsService {
  //itemsPerPage = ITEMS_PER_PAGE;

  //constructor() { }
  /*   messages: string[] = [];

  add(message: string) {
    this.messages.push(message);
  }

  clear() {
    this.messages = [];
  } */
  public static cleanupMask(strMaskedData: any): any {
    if (strMaskedData === null || strMaskedData === '' || strMaskedData === undefined) {
      return (strMaskedData = undefined);
    } else return strMaskedData.replace(/\D+/g, '');
  }

  public getEntityStatus(status: boolean): string {
    if (status) {
      return 'Ativo';
    } else return 'Inativo';
  }

  public buildCurrentSearch(myQueryObject: Object, searchParameter: string): Object {
    // let myQueryObjectTemp: { [k: string]: any } = {};
    //myQueryObjectTemp = myQueryObject;
    ///const pageToLoad: number = page || 1;

    //myQueryObjectTemp = { page: pageToLoad - 1, size: this.itemsPerPage, sort: this.sort() };
    //const searchParameter: string = this.searchForm.get(['searchParamRadio'])!.value;

    /// Add a new object key/value. Ex: this.objTeste["key3"] = "value3";
    if (searchParameter !== null) {
      myQueryObject[searchParameter + '.contains'] = searchParameter;
    }

    return myQueryObject;
  }
}
