import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPlanoEstrategico } from 'app/shared/model/plano-estrategico.model';
import { PlanoEstrategicoService } from './plano-estrategico.service';

@Component({
  templateUrl: './plano-estrategico-delete-dialog.component.html',
})
export class PlanoEstrategicoDeleteDialogComponent {
  planoEstrategico?: IPlanoEstrategico;

  constructor(
    protected planoEstrategicoService: PlanoEstrategicoService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.planoEstrategicoService.delete(id).subscribe(() => {
      this.eventManager.broadcast('planoEstrategicoListModification');
      this.activeModal.close();
    });
  }
}
