import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ClinicaAdaptrojhAppSharedModule } from 'app/shared/shared.module';
import { PlanoEstrategicoComponent } from './plano-estrategico.component';
import { PlanoEstrategicoDetailComponent } from './plano-estrategico-detail.component';
import { PlanoEstrategicoUpdateComponent } from './plano-estrategico-update.component';
import { PlanoEstrategicoDeleteDialogComponent } from './plano-estrategico-delete-dialog.component';
import { planoEstrategicoRoute } from './plano-estrategico.route';

@NgModule({
  imports: [ClinicaAdaptrojhAppSharedModule, RouterModule.forChild(planoEstrategicoRoute)],
  declarations: [
    PlanoEstrategicoComponent,
    PlanoEstrategicoDetailComponent,
    PlanoEstrategicoUpdateComponent,
    PlanoEstrategicoDeleteDialogComponent,
  ],
  entryComponents: [PlanoEstrategicoDeleteDialogComponent],
})
export class ClinicaAdaptrojhAppPlanoEstrategicoModule {}
