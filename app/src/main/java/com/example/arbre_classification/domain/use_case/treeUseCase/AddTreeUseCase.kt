package com.example.arbre_classification.domain.use_case.treeUseCase

import com.example.arbre_classification.domain.repository.TreeRepository
import javax.inject.Inject

class AddTreeUseCase @Inject constructor(
    private val repository: TreeRepository
) {

    /*
    On pourrait ici mettre en place un code pour POST un nouvel arbre
    sur l'api (si l'on pouvait) via le repository
    */

}