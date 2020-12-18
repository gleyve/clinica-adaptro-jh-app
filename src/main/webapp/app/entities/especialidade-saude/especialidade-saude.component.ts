import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IEspecialidadeSaude } from 'app/shared/model/especialidade-saude.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { EspecialidadeSaudeService } from './especialidade-saude.service';
import { EspecialidadeSaudeDeleteDialogComponent } from './especialidade-saude-delete-dialog.component';
//import { MyAdaptroUtilsService } from 'app/shared/util/my-adaptro-util.service';

@Component({
  selector: 'jhi-especialidade-saude',
  templateUrl: './especialidade-saude.component.html',
})
export class EspecialidadeSaudeComponent implements OnInit, OnDestroy {
  //static myAdaptroUtilsService: MyAdaptroUtilsService;
  especialidadeSaudes: IEspecialidadeSaude[];
  eventSubscriber?: Subscription;
  itemsPerPage: number;
  links: any;
  page: number;
  predicate: string;
  ascending: boolean;

  constructor(
    protected especialidadeSaudeService: EspecialidadeSaudeService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal,
    protected parseLinks: JhiParseLinks
  ) {
    this.especialidadeSaudes = [];
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.page = 0;
    this.links = {
      last: 0,
    };
    this.predicate = 'id';
    this.ascending = true;
  }

  loadAll(): void {
    this.especialidadeSaudeService
      .query({
        page: this.page,
        size: this.itemsPerPage,
        sort: this.sort(),
      })
      .subscribe((res: HttpResponse<IEspecialidadeSaude[]>) => this.paginateEspecialidadeSaudes(res.body, res.headers));
  }

  reset(): void {
    this.page = 0;
    this.especialidadeSaudes = [];
    this.loadAll();
  }

  loadPage(page: number): void {
    this.page = page;
    this.loadAll();
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInEspecialidadeSaudes();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IEspecialidadeSaude): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInEspecialidadeSaudes(): void {
    this.eventSubscriber = this.eventManager.subscribe('especialidadeSaudeListModification', () => this.reset());
  }

  delete(especialidadeSaude: IEspecialidadeSaude): void {
    const modalRef = this.modalService.open(EspecialidadeSaudeDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.especialidadeSaude = especialidadeSaude;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateEspecialidadeSaudes(data: IEspecialidadeSaude[] | null, headers: HttpHeaders): void {
    const headersLink = headers.get('link');
    this.links = this.parseLinks.parse(headersLink ? headersLink : '');
    if (data) {
      for (let i = 0; i < data.length; i++) {
        this.especialidadeSaudes.push(data[i]);
      }
    }
  }

  public getEntityStatus(status: boolean): string {
    if (status) {
      return 'Ativo';
    } else return 'Inativo';
  }
}
