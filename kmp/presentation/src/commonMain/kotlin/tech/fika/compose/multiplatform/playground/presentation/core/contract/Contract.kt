package tech.fika.compose.multiplatform.playground.presentation.core.contract

sealed interface Contract

interface Action : Contract

interface Event : Contract

interface State : Contract

interface Message : Contract
