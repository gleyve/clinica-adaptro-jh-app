import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, ParamMap, Router, Data } from '@angular/router';
import { Subscription, combineLatest } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { FormBuilder, Validators, FormGroup, FormControl, CheckboxRequiredValidator } from '@angular/forms';
import { IFornecedor } from 'app/shared/model/fornecedor.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { FornecedorService } from './fornecedor.service';
import { FornecedorDeleteDialogComponent } from './fornecedor-delete-dialog.component';
import { TipoPessoa } from 'app/shared/model/enumerations/tipo-pessoa.model';

@Component({
  selector: 'jhi-fornecedor',
  templateUrl: './fornecedor.component.html',
})
export class FornecedorComponent implements OnInit, OnDestroy {
  fornecedors?: IFornecedor[];
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
    protected fornecedorService: FornecedorService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  search(): void {
    if (this.searchForm.get(['valorBusca'])!.value !== null) {
      this.myQueryObject = this.buildCurrentSearch(1);
      this.performSearch(this.myQueryObject, true);
    }
  }

  buildCurrentSearch(page?: number): Object {
    const pageToLoad: number = page || this.page || 1;

    const searchParameter: string = this.searchForm.get(['searchParamRadio'])!.value;
    switch (searchParameter) {
      case 'nome': {
        this.myQueryObject = {
          page: pageToLoad - 1,
          size: this.itemsPerPage,
          sort: this.sort(),
          'nome.contains': this.searchForm.get(['valorBusca'])!.value,
        };
        break;
      }
      case 'fantasia': {
        this.myQueryObject = {
          page: pageToLoad - 1,
          size: this.itemsPerPage,
          sort: this.sort(),
          'nomeFantasia.contains': this.searchForm.get(['valorBusca'])!.value,
        };
        break;
      }
      case 'cpf': {
        this.myQueryObject = {
          page: pageToLoad - 1,
          size: this.itemsPerPage,
          sort: this.sort(),
          'numeroCPF.equals': this.searchForm.get(['valorBusca'])!.value,
        };
        break;
      }
      case 'cnpj': {
        this.myQueryObject = {
          page: pageToLoad - 1,
          size: this.itemsPerPage,
          sort: this.sort(),
          'numeroCNPJ.equals': this.searchForm.get(['valorBusca'])!.value,
        };
        break;
      }
      default: {
        break;
      }
    }

    return this.myQueryObject;
  }

  private initSearchForm(): void {
    this.searchForm = new FormGroup({
      email: new FormControl(null, Validators.required),
      valorBusca: new FormControl(null, Validators.required),
      searchParamRadio: new FormControl('nome', Validators.required),
      //searchParamCheckAtivo: new FormControl(true)
    });
  }

  onClear(): void {
    //this.searchForm.reset();
    this.searchForm = new FormGroup({
      email: new FormControl(null, Validators.required),
      valorBusca: new FormControl(null, Validators.required),
      searchParamRadio: new FormControl('nome', Validators.required),
      //searchParamCheckAtivo: new FormControl(true)
    });
  }

  loadPage(page?: number, dontNavigate?: boolean): void {
    const pageToLoad: number = page || this.page || 1;
    this.fornecedorService
      .query({
        page: pageToLoad - 1,
        size: this.itemsPerPage,
        sort: this.sort(),
      })
      .subscribe(
        (res: HttpResponse<IFornecedor[]>) => this.onSuccess(res.body, res.headers, pageToLoad, !dontNavigate),
        () => this.onError()
      );
  }

  ngOnInit(): void {
    this.handleNavigation();
    this.registerChangeInFornecedors();
    this.initSearchForm();
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

  trackId(index: number, item: IFornecedor): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInFornecedors(): void {
    this.eventSubscriber = this.eventManager.subscribe('fornecedorListModification', () => this.loadPage());
  }

  delete(fornecedor: IFornecedor): void {
    const modalRef = this.modalService.open(FornecedorDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.fornecedor = fornecedor;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  testaTipoPessoa(tipoPessoa: TipoPessoa, vlr: string): boolean {
    if (tipoPessoa.toString() === vlr) {
      return true;
    } else return false;
  }

  performSearch(queryObject: Object, dontNavigate?: boolean): void {
    const pageToLoad: number = 1 || this.page || 1;
    this.fornecedorService.query(queryObject).subscribe(
      (res: HttpResponse<IFornecedor[]>) => this.onSuccess(res.body, res.headers, pageToLoad, !dontNavigate),
      () => this.onError()
    );
  }

  protected onSuccess(data: IFornecedor[] | null, headers: HttpHeaders, page: number, navigate: boolean): void {
    this.totalItems = Number(headers.get('X-Total-Count'));
    this.page = page;
    if (navigate) {
      this.router.navigate(['/fornecedor'], {
        queryParams: {
          page: this.page,
          size: this.itemsPerPage,
          sort: this.predicate + ',' + (this.ascending ? 'asc' : 'desc'),
        },
      });
    }
    this.fornecedors = data || [];
    this.ngbPaginationPage = this.page;
  }

  protected onError(): void {
    this.ngbPaginationPage = this.page ?? 1;
  }
}
