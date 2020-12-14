import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, ParamMap, Router, Data } from '@angular/router';
import { Subscription, combineLatest } from 'rxjs';
import { JhiEventManager, JhiDataUtils } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ICliente } from 'app/shared/model/cliente.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { ClienteService } from './cliente.service';
import { ClienteDeleteDialogComponent } from './cliente-delete-dialog.component';
import { Validators, FormGroup, FormControl } from '@angular/forms';

@Component({
  selector: 'jhi-cliente',
  templateUrl: './cliente.component.html',
})
export class ClienteComponent implements OnInit, OnDestroy {
  clientes?: ICliente[];
  eventSubscriber?: Subscription;
  totalItems = 0;
  itemsPerPage = ITEMS_PER_PAGE;
  page!: number;
  predicate!: string;
  ascending!: boolean;
  ngbPaginationPage = 1;
  searchForm!: FormGroup;
  myQueryObject!: Object;

  constructor(
    protected clienteService: ClienteService,
    protected activatedRoute: ActivatedRoute,
    protected dataUtils: JhiDataUtils,
    protected router: Router,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  search(): void {
    //this.myQueryObject = this.buildCurrentSearch2(1);
    //this.performSearch(this.myQueryObject, true);
    this.loadPage();
  }

  /*   loadPage(page?: number, dontNavigate?: boolean): void {
    const pageToLoad: number = page || this.page || 1;

    this.clienteService
      .query({
        page: pageToLoad - 1,
        size: this.itemsPerPage,
        sort: this.sort(),
      })
      .subscribe(
        (res: HttpResponse<ICliente[]>) => this.onSuccess(res.body, res.headers, pageToLoad, !dontNavigate),
        () => this.onError()
      );
  } */

  loadPage(page?: number, dontNavigate?: boolean): void {
    const pageToLoad: number = page || this.page || 1;
    this.myQueryObject = this.buildCurrentSearch2(1);
    /*     this.myQueryObject ={
        page: pageToLoad - 1,
        size: this.itemsPerPage,
        sort: this.sort(),
    } */

    this.clienteService.query(this.myQueryObject).subscribe(
      (res: HttpResponse<ICliente[]>) => this.onSuccess(res.body, res.headers, pageToLoad, !dontNavigate),
      () => this.onError()
    );
  }

  ngOnInit(): void {
    this.initSearchForm();
    this.handleNavigation();
    this.registerChangeInClientes();
  }

  protected handleNavigation(): void {
    combineLatest(this.activatedRoute.data, this.activatedRoute.queryParamMap, (data: Data, params: ParamMap) => {
      const page = params.get('page');
      const pageNumber = page !== null ? +page : 1;
      const sort = (params.get('sort') ?? data['defaultSort']).split(',');
      const predicate = sort[0];
      const ascending = sort[1] === 'asc';
      if (pageNumber !== this.page || predicate !== this.predicate || ascending !== this.ascending) {
        this.predicate = predicate;
        this.ascending = ascending;
        this.loadPage(pageNumber, true);
      }
    }).subscribe();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ICliente): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  byteSize(base64String: string): string {
    return this.dataUtils.byteSize(base64String);
  }

  openFile(contentType = '', base64String: string): void {
    return this.dataUtils.openFile(contentType, base64String);
  }

  registerChangeInClientes(): void {
    this.eventSubscriber = this.eventManager.subscribe('clienteListModification', () => this.loadPage());
  }

  delete(cliente: ICliente): void {
    const modalRef = this.modalService.open(ClienteDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.cliente = cliente;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  buildCurrentSearch2(page?: number): Object {
    let myQueryObjectTemp: { [k: string]: any } = {};
    const pageToLoad: number = page || this.page || 1;

    myQueryObjectTemp = { page: pageToLoad - 1, size: this.itemsPerPage, sort: this.sort() };
    const searchParameter: string = this.searchForm.get(['searchParamRadio'])!.value;
    const searchParameterAtivo: string = this.searchForm.get(['searchParamCheckAtivo'])!.value;
    const searchParameterInativo: string = this.searchForm.get(['searchParamCheckInativo'])!.value;

    // Ativo/Inativo
    if (searchParameterAtivo && searchParameterInativo) {
      // Trazer todos, não precisa especificar filtro
    } else if (searchParameterAtivo && !searchParameterInativo) {
      myQueryObjectTemp['ativo.equals'] = true;
    } else if (!searchParameterAtivo && searchParameterInativo) {
      myQueryObjectTemp['ativo.equals'] = false;
    }

    /// Add a new object key/value. Ex: this.objTeste["key3"] = "value3";
    if (this.searchForm.get(['valorBusca'])!.value !== null) {
      myQueryObjectTemp[searchParameter + '.contains'] = this.searchForm.get(['valorBusca'])!.value;
    }

    return myQueryObjectTemp;
  }

  performSearch(queryObject: Object, dontNavigate?: boolean): void {
    const pageToLoad: number = this.page || 1;
    this.clienteService.query(queryObject).subscribe(
      (res: HttpResponse<ICliente[]>) => this.onSuccess(res.body, res.headers, pageToLoad, !dontNavigate),
      () => this.onError()
    );
  }

  private initSearchForm(): void {
    this.searchForm = new FormGroup({
      email: new FormControl(null, Validators.required),
      valorBusca: new FormControl(null, Validators.required),
      searchParamRadio: new FormControl('nomeCompleto', Validators.required),
      searchParamCheckAtivo: new FormControl(true),
      searchParamCheckInativo: new FormControl(true),
    });
  }

  onClear(): void {
    //this.searchForm.reset();
    this.searchForm = new FormGroup({
      email: new FormControl(null, Validators.required),
      valorBusca: new FormControl(null, Validators.required),
      searchParamRadio: new FormControl('nomeCompleto', Validators.required),
      searchParamCheckAtivo: new FormControl(true),
      searchParamCheckInativo: new FormControl(true),
    });
  }

  protected onSuccess(data: ICliente[] | null, headers: HttpHeaders, page: number, navigate: boolean): void {
    this.totalItems = Number(headers.get('X-Total-Count'));
    this.page = page;
    if (navigate) {
      this.router.navigate(['/cliente'], {
        queryParams: {
          page: this.page,
          size: this.itemsPerPage,
          sort: this.predicate + ',' + (this.ascending ? 'asc' : 'desc'),
        },
      });
    }
    this.clientes = data || [];
    this.ngbPaginationPage = this.page;
  }

  protected onError(): void {
    this.ngbPaginationPage = this.page ?? 1;
  }
}
