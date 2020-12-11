import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEspecialidadeSaude } from 'app/shared/model/especialidade-saude.model';
import { EspecialidadeSaudeService } from './especialidade-saude.service';

@Component({
  templateUrl: './especialidade-saude-delete-dialog.component.html',
})
export class EspecialidadeSaudeDeleteDialogComponent {
  especialidadeSaude?: IEspecialidadeSaude;

  constructor(
    protected especialidadeSaudeService: EspecialidadeSaudeService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.especialidadeSaudeService.delete(id).subscribe(() => {
      this.eventManager.broadcast('especialidadeSaudeListModification');
      this.activeModal.close();
    });
  }
}
